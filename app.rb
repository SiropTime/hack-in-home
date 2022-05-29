require 'telegram/bot'
require 'dotenv'
require 'logger'
require_relative 'lib/listener'
Dir[File.dirname(__FILE__) + '/lib/*.rb'].each { |file| require_relative file }

class TelegramBotApp
  TOKEN = ENV['PRODUCTION_TOKEN']
  LOGGER = Logger.new($stderr)

  def initialize
    @client = Telegram::Bot::Client.new(TOKEN, logger: LOGGER)

    @client.run do |bot|
      listener = Bot::Listener.new(bot)

      bot.listen do |message|
        begin
          Thread.start(message) do |rqst|
            listener.call(rqst)
          end
        rescue => e
          bot.logger.error(e.message)
        end
      end
    end
  end
end
