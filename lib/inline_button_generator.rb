require 'telegram/bot'

module Bot
  class InlineButtonGenerator
    def self.create(name, data)
      Telegram::Bot::Types::InlineKeyboardButton.new(text: "#{data['id']}. #{data['name']}", callback_data: "#{name}_#{data['id']}")
    end

    def self.callback_data(name, data)
      "#{name}_#{data['id']}"
    end
  end
end
