require 'telegram/bot'
require_relative 'base_bot'

module Bot
  class Schedule < BaseBot
    def listen(message)
      @bot.api.send_message(chat_id: message.from.id, text: "https://edu.stankin.ru/mod/folder/view.php?id=255341")
    end
  end
end
