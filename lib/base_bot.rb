require 'telegram/bot'
require 'dotenv/load'

module Bot
  class BaseBot
    attr_accessor :bot, :messages

    def initialize(bot)
      @bot = bot
      @messages = []
    end

    def previous_message
      @messages.last
    end
  end
end
