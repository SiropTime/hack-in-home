require 'telegram/bot'
require 'json'
require 'time'
require 'date'
require_relative 'base_bot'
require_relative 'request'
require_relative 'objects/subject'
require_relative 'inline_button_generator'

module Bot
  class SubjectsController < BaseBot
    attr_accessor :arr_subjects

    def listen(message)
      if message.data.split('_').first == 'subject'
        @arr_subjects.each do |subject|
          @bot.api.send_message(chat_id: message.from.id, text: info(subject)) if message.data == subject.callback_data
        end
      end
    end

    def subjects_list(message)
      response = Request.send_data(@bot, 'subjects')
      parse_subjects(response)
      ikb = []
      @arr_subjects.each do |subject|
        @bot.logger.info "subject: #{subject.callback_data}"
        ikb << subject.inline_button
      end
      inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
      @bot.logger.info('Send from subjects controller')
      @bot.api.send_message(chat_id: message.from.id, text: "Список предметов", reply_markup: inline_markup)
    end

    private

    def info(obj)
      str = " "
      str << obj.id.to_s << '. '
      str << obj.name << ', '
      str << obj.url << ', ' unless obj.url.nil?
      d = DateTime.parse(obj.module_start_date) unless obj.module_start_date.nil?
      str << 'Начало модуля: ' << d.strftime('%d-%m-%Y') << ', ' unless d.nil?
      d = DateTime.parse(obj.module_end_date) unless obj.module_end_date.nil?
      str << 'Конец модуля: ' << d.strftime('%d-%m-%Y') << '.' unless d.nil?
      str
    end

    def parse_subjects(json)
      @arr_subjects = []
      json.each do |h|
        button = []
        button << Bot::InlineButtonGenerator.create('subject', h)
        button << Bot::InlineButtonGenerator.callback_data('subject', h)
        @arr_subjects << Subject.new(h, button)
      end
    end
  end
end
