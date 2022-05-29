require 'open-uri'
require 'cgi'
require 'net/http'
require 'json'
require_relative 'request'
require_relative 'base_bot'
require_relative 'objects/teacher'
require_relative 'inline_button_generator'

module Bot
  class TeachersController < BaseBot
    attr_accessor :arr_teachers, :teacher

    URL = 'https://rinh-api.kovalev.team'
    URL_DTO = 'https://rinh-api.kovalev.team/employee/dto/'

    def listen(message)
      if message.data.split('_').first == 'teacher'
        @arr_teachers.each do |teacher|
          @bot.api.send_message(chat_id: message.from.id, text: info(teacher)) if message.data == teacher.callback_data
        end
      end
    end

    def send_list(message)
      data = CGI.escape message.text
      json = find_by_surname(data)
      if !json.empty?
        @arr_teachers = []
        count = 0
        json.each do |value|
          return if count == 5

          count += 1
          @teacher = Teacher.new(value)
          data = all_info(@teacher.id)
          parse_info(data)
          @arr_teachers << @teacher
          ikb = [@teacher.inline_button]
          inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
          @bot.api.send_message(chat_id: message.from.id, text: "#{value['fullName']}", reply_markup: inline_markup)
        end
      else
        @bot.api.send_message(chat_id: message.from.id, text: "Ничего не найдено. Введите ещё раз")
      end
    end

    def send_info(message)
      @bot.api.send_message(chat_id: message.from.id, text: "Введите фамилию преподавателя")
    end

    private

    def find_by_surname(data)
      uri = URI(URL + "/employee/surname/#{data}")
      response = Net::HTTP.get(uri)
      @bot.logger.info(response)
      JSON.parse(response)
    end

    def all_info(id)
      uri = URI(URL_DTO + "#{id}")
      response = Net::HTTP.get(uri)
      @bot.logger.info(response)
      JSON.parse(response)
    end

    def parse_info(data)
      employee = data['employee']
      @teacher.email = employee['email']
      @teacher.phone = employee['phone']
      @teacher.inline_button = Bot::InlineButtonGenerator.create('teacher', { 'id' => @teacher.id, 'name' => @teacher.full_name })
      @teacher.callback_data = Bot::InlineButtonGenerator.callback_data('teacher', { 'id' => @teacher.id, 'name' => @teacher.full_name })
    end

    def info(obj)
      str = ""
      str << obj.full_name << ', '
      str << obj.email << ', '
      str << obj.phone
      str
    end
  end
end
