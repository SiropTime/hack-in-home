require 'net/http'
require 'uri'
require 'json'
require_relative '../base_bot'

module Bot
  class Authorization < BaseBot
    attr_accessor :authenticated, :login, :password

    def login
      @bot.api.send_message(chat_id: @message.chat.id, text: "Введите логин:")
    end

    def password
      @bot.api.send_message(chat_id: @message.chat.id, text: "Введите пароль:")
    end

    def auth_to_module
      data = { login: @login, password: @password }
      json ||= JSON.generate(data)
      uri = URI('https://lk.stankin.ru/#!login')
      http = Net::HTTP.new(uri.host, uri.port)

      request = Net::HTTP::Post.new(uri.path)
      response = http.request(request) # TODO: настроить авторизацию
      auth_success
    end

    private

    def auth_success
      @authenticated = true
    end

    def auth_failed
      @authenticated = false
    end

    def check_elder
      true
    end
  end
end
