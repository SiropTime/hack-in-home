require 'net/http'
require 'open-uri'
require 'json'

module Bot
  class Request
    class << self
      URL = 'http://25.6.173.125:8080'

      def send_data(bot, data, options = {})
        case data
        when 'subjects'
          request_subject_get_all(bot)
        when 'proposal'
          request_proposal_post(bot, options)
        when 'student'
          request_students_all(bot)
        end
      end

      private

      def request_subject_get_all(bot)
        uri = URI(URL + '/subject/list')
        response = Net::HTTP.get(uri)
        bot.logger.info(response)
        JSON.parse(response)
      end

      def request_proposal_post(bot, options)
        uri = URI(URL + '/requisition/save')
        puts "#{options}"
        response = Net::HTTP.post(uri, JSON.generate(options), "Content-Type" => "application/json")
        bot.logger.info(response)
      end

      def request_students_all(bot)
        uri = URI(URL + '/student/list')
        response = Net::HTTP.get(uri)
        bot.logger.info(response)
        JSON.parse(response)
      end
    end
  end
end
