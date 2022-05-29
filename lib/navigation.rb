require 'telegram/bot'
require 'uri'
require 'net/http'
require 'open-uri'
require_relative 'assets/inline_button'
require_relative 'assets/keyboard_button'
# require 'RMagick'
require_relative 'base_bot'

module Bot
  class Navigation < BaseBot
    # include Magick

    def listen(message)
      case message.data
      when 'navigation_1_stage_new'
        send_stage(message, 1, 'new')
      when 'navigation_2_stage_new'
        send_stage(message, 2, 'new')
      when 'navigation_3_stage_new'
        send_stage(message, 3, 'new')
      when 'navigation_4_stage_new'
        send_stage(message, 4, 'new')
      when 'navigation_5_stage_new'
        send_stage(message, 5, 'new')
      when 'navigation_6_stage_new'
        send_stage(message, 6, 'new')
      when 'navigation_8_stage_new'
        send_stage(message, 8, 'new')
      when 'navigation_9_stage_new'
        send_stage(message, 9, 'new')
      when 'navigation_1_stage_old'
        send_stage(message, 1, 'old')
      when 'navigation_2_stage_old'
        send_stage(message, 2, 'old')
      when 'navigation_3_stage_old'
        send_stage(message, 3, 'old')
      when 'navigation_4_left_stage_old'
        send_stage(message, 4, 'old')
      when 'navigation_4_main_stage_old'
        send_stage(message, 5, 'old')
      when 'navigation_4_right_stage_old'
        send_stage(message, 6, 'old')
      when 'navigation_5_stage_old'
        send_stage(message, 7, 'old')
      when 'navigation_transition'
        send_stage(message, 0, 'transition')
      when 'corpus_new'
        send_buttons_new_stages(message)
      when 'corpus_old'
        send_buttons_old_stages(message)
      when 'corpus_transition'
        send_buttons_transition_stage(message)
      end
    end

    def send_buttons_corpuses(message)
      @bot.logger.info "#{Bot::InlineButton::Corpus::TRANSITION}"
      ikb = [Bot::InlineButton::Corpus::NEW_CORPUS, Bot::InlineButton::Corpus::OLD_CORPUS,
             Bot::InlineButton::Corpus::TRANSITION, Bot::InlineButton::BACK]
      inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
      @bot.logger.info('Send inline navigation corpuses')
      @bot.api.send_message(chat_id: message.from.id, text: "Выберите корпус", reply_markup: inline_markup)
    end

    private

    def send_stage(message, stage, corpus)
      @bot.api.send_document(chat_id: message.from.id, document: document(stage, corpus))
    end

    def document(num, corpus)
      case corpus
      when 'new'
        case num
        when 1 then "https://vstankine.ru/sites/default/files/1_etazh_novy_korpus_12_1.pdf"
        when 2 then "https://vstankine.ru/sites/default/files/novyy_korpus_2_etazh_12.pdf"
        when 3 then "https://vstankine.ru/sites/default/files/novyy_korpus_3_etazh_12.pdf"
        when 4 then "https://vstankine.ru/sites/default/files/novyy_korpus_4_etazh_12.pdf"
        when 5 then "https://vstankine.ru/sites/default/files/novyy_korpus_5_etazh.pdf"
        when 6 then "https://vstankine.ru/sites/default/files/novyy_korpus_6_etazh.pdf"
        when 8 then "https://vstankine.ru/sites/default/files/novyy_korpus_8_etazh.pdf"
        when 9 then "https://vstankine.ru/sites/default/files/novyy_korpus_9_etazh.pdf"
        end
      when 'old'
        case num
        when 1 then "https://vstankine.ru/sites/default/files/staryy_korpus_1_etazh.pdf"
        when 2 then "https://vstankine.ru/sites/default/files/staryy_korpus_2_etazh_12.pdf"
        when 3 then "https://vstankine.ru/sites/default/files/staryy_korpus_3_etazh_levoe_krylo.pdf"
        when 4 then "https://vstankine.ru/sites/default/files/staryy_korpus_4_etazh_levoe_krylo.pdf"
        when 5 then "https://vstankine.ru/sites/default/files/staryy_korpus_4_etazh_po_glavnoy_lestnice.pdf"
        when 6 then "https://vstankine.ru/sites/default/files/staryy_korpus_4_etazh_pravoe_krylo.pdf"
        when 7 then "https://vstankine.ru/sites/default/files/staryy_korpus_5_etazh.pdf"
        end
      when 'transition'
        "https://vstankine.ru/sites/default/files/perehod_12_versiya.pdf"
      end
    end

    def send_buttons_new_stages(message)
      ikb = [Bot::InlineButton::Navigation::FIRST_STAGE_NEW, Bot::InlineButton::Navigation::SECOND_STAGE_NEW,
             Bot::InlineButton::Navigation::THIRD_STAGE_NEW,  Bot::InlineButton::Navigation::FOURTH_STAGE_NEW,
             Bot::InlineButton::Navigation::FIFTH_STAGE_NEW,  Bot::InlineButton::Navigation::SIXTH_STAGE_NEW,
             Bot::InlineButton::Navigation::EIGHTH_STAGE_NEW, Bot::InlineButton::Navigation::NINTH_STAGE_NEW,
             Bot::InlineButton::BACK]
      inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
      @bot.logger.info('Send inline navigation stages')
      @bot.api.send_message(chat_id: message.from.id, text: "Выберите этаж", reply_markup: inline_markup)
    end

    def send_buttons_old_stages(message)
      ikb = [Bot::InlineButton::Navigation::FIRST_STAGE_OLD, Bot::InlineButton::Navigation::SECOND_STAGE_OLD,
             Bot::InlineButton::Navigation::THIRD_STAGE_OLD, Bot::InlineButton::Navigation::FOURTH_LEFT_STAGE_OLD,
             Bot::InlineButton::Navigation::FOURTH_MAIN_STAGE_OLD, Bot::InlineButton::Navigation::FOURTH_RIGHT_STAGE_OLD,
             Bot::InlineButton::Navigation::FIFTH_STAGE_OLD, Bot::InlineButton::BACK]
      inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
      @bot.logger.info('Send inline navigation stages')
      @bot.api.send_message(chat_id: message.from.id, text: "Выберите этаж", reply_markup: inline_markup)
    end

    def send_buttons_transition_stage(message)
      ikb = [Bot::InlineButton::Navigation::TRANSITION,
             Bot::InlineButton::BACK]
      inline_markup = Telegram::Bot::Types::InlineKeyboardMarkup.new(inline_keyboard: ikb)
      @bot.logger.info('Send inline navigation stages')
      @bot.api.send_message(chat_id: message.from.id, text: "Выберите этаж", reply_markup: inline_markup)
    end

    # def to_image
    #   uri = URI("https://vstankine.ru/sites/default/files/1_etazh_novy_korpus_12_1.pdf")
    #   response = Net::HTTP.get_response(uri)
    #   image = Magick::ImageList.new(response.body)
    #   image.write('image.jpg')
    # end
  end
end
