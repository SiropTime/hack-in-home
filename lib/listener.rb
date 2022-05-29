require 'telegram/bot'
require_relative 'assets/keyboard_button'
require_relative 'assets/inline_button'
require_relative 'users/authorization'
require_relative 'subjects_controller'
require_relative 'navigation'

module Bot
  class Listener
    attr_reader :bot, :message

    def initialize(bot)
      @bot = bot
      @auth = Bot::Authorization.new(@bot)
      @navigation = Bot::Navigation.new(@bot)
      @subject = Bot::SubjectsController.new(@bot)
      @schedule = Bot::Schedule.new(@bot)
      @teacher = Bot::TeachersController.new(@bot)
      @proposal = Bot::ProposalController.new(@bot)
      @student = Bot::StudentsController.new(@bot)
    end

    def call(message)
      @message = message

      begin
        case @message
        when Telegram::Bot::Types::CallbackQuery
          query_call
        when Telegram::Bot::Types::Message
          message_call
        end
      rescue => e
        @bot.logger.error(e.message)
        @bot.logger.error(e.backtrace)
      end
    end

    def message_call
      if @auth.authenticated
        parse_message
      else
        case @message.text
        when '/start'
          if !@auth.authenticated
            kb = [Bot::KeyboardButton::AUTHORIZATION]
            markup = Telegram::Bot::Types::ReplyKeyboardMarkup.new(keyboard: kb, resize_keyboard: true)
            @bot.logger.info('Bot has been started working')
            @bot.api.send_message(chat_id: @message.chat.id, text: 'Приветствую в боте!', reply_markup: markup)
          else
            kb = [[Bot::KeyboardButton::TEACHERS, Bot::KeyboardButton::GET_SCHEDULE],
                  [Bot::KeyboardButton::GET_NAVIGATION, Bot::KeyboardButton::GET_SUBJECTS],
                  [Bot::KeyboardButton::PROPOSAL, Bot::KeyboardButton::DEBT]]
            markup = Telegram::Bot::Types::ReplyKeyboardMarkup.new(keyboard: kb, resize_keyboard: true)
            @bot.api.send_message(chat_id: @message.chat.id, text: "Авторизация прошла успешно", reply_markup: markup)
          end

        when 'Авторизация'
          @auth.auth_to_module
          if @auth.authenticated
            kb = [[Bot::KeyboardButton::TEACHERS, Bot::KeyboardButton::GET_SCHEDULE],
                  [Bot::KeyboardButton::GET_NAVIGATION, Bot::KeyboardButton::GET_SUBJECTS],
                  [Bot::KeyboardButton::PROPOSAL, Bot::KeyboardButton::DEBT],
                  Bot::KeyboardButton::Elder::GROUP_LIST]
            markup = Telegram::Bot::Types::ReplyKeyboardMarkup.new(keyboard: kb, resize_keyboard: true)
            @bot.logger.info('Авторизация')
            @bot.api.send_message(chat_id: @message.chat.id, text: "Авторизация прошла успешно", reply_markup: markup)
          else
            @bot.api.send_message(chat_id: @message.from.id, text: "Ошибка при аторизации")
          end
        end
      end
    end

    def query_call
      parse_query if @auth.authenticated
    end

    private

    def parse_query
      case @message.data.split('_').first
      when 'navigation'
        @navigation.listen(@message)
      when 'corpus'
        @navigation.listen(@message)
      when 'subject'
        @subject.listen(@message)
      when 'schedule'
        @schedule.listen(@message)
      when 'proposal'
        @proposal.listen(@message)
      when 'teacher'
        @teacher.listen(@message)
      when 'back'
        kb = [[Bot::KeyboardButton::TEACHERS, Bot::KeyboardButton::GET_SCHEDULE],
              [Bot::KeyboardButton::GET_NAVIGATION, Bot::KeyboardButton::GET_SUBJECTS],
              [Bot::KeyboardButton::PROPOSAL, Bot::KeyboardButton::DEBT]]
        markup = Telegram::Bot::Types::ReplyKeyboardMarkup.new(keyboard: kb, resize_keyboard: true)
        @bot.logger.info('Назад')
        # TODO: delete previous message
        @bot.api.send_message(chat_id: @message.from.id, text: "Назад", reply_markup: markup)
      end
    end

    def parse_message
      case @message.text
      when "Навигация по ВУЗу"
        @navigation.send_buttons_corpuses(@message)
      when 'Поиск преподавателя'
        @teacher.send_info(@message)
      when 'Изучаемые предметы'
        @subject.subjects_list(@message)
      when "Заявка"
        @proposal.send_info(@message)
      when "Расписание"
        @schedule.listen(@message)
      when "Долги"
        @bot.api.send_message(chat_id: @message.from.id, text: "Долги")
      when 'Список группы'
        @student.students_list(@message)
      when ->(n) { n.to_s.chars.last == '?' }
        @proposal.listen(@message)
      else
        @teacher.send_list(@message)
      end
    end
  end
end
