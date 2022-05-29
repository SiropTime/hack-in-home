require 'telegram/bot'
require 'json'
require_relative 'base_bot'
require_relative 'request'
require_relative 'objects/student'

module Bot
  class StudentsController < BaseBot
    attr_accessor :arr_students

    def listen(message)

    end

    def students_list(message)
      response = Request.send_data(@bot, 'student')
      parse_students(response)
      @bot.api.send_message(chat_id: message.from.id, text: list)
    end

    private

    def list
      str = ""
      id = 0
      @arr_students.each do |student|
        id += 1
        str << "#{id}. #{student.name} #{student.middle_name} #{student.surname}, email: #{student.email}, группа: #{student.group}\n"
      end
      str
    end

    def parse_students(json)
      @arr_students = []
      json.each do |h|
        @arr_students << Student.new(h)
      end
    end
  end
end
