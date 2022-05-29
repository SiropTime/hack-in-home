require 'telegram/bot'

module Bot
  module InlineButton
    BACK ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Назад', callback_data: 'back')

    module FAQ
      SEND_REQUEST ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Заявка отправлена', callback_data: 'faq_request')
    end

    module Schedule
      WEEK ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'На неделю', callback_data: 'schedule_week')
    end

    module Corpus
      NEW_CORPUS ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Новый корпус', callback_data: 'corpus_new')
      OLD_CORPUS ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Старый корпус', callback_data: 'corpus_old')
      TRANSITION ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Переход', callback_data: 'corpus_transition')
    end

    module Navigation
      FIRST_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '1 этаж', callback_data: 'navigation_1_stage_new')
      SECOND_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '2 этаж', callback_data: 'navigation_2_stage_new')
      THIRD_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '3 этаж', callback_data: 'navigation_3_stage_new')
      FOURTH_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '4 этаж', callback_data: 'navigation_4_stage_new')
      FIFTH_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '5 этаж', callback_data: 'navigation_5_stage_new')
      SIXTH_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '6 этаж', callback_data: 'navigation_6_stage_new')
      EIGHTH_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '8 этаж', callback_data: 'navigation_8_stage_new')
      NINTH_STAGE_NEW ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '9 этаж', callback_data: 'navigation_9_stage_new')
      FIRST_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '1 этаж', callback_data: 'navigation_1_stage_old')
      SECOND_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '2 этаж', callback_data: 'navigation_2_stage_old')
      THIRD_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '3 этаж(левое крыло)', callback_data: 'navigation_3_stage_old')
      FOURTH_LEFT_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '4 этаж(левое крыло)', callback_data: 'navigation_4_left_stage_old')
      FOURTH_MAIN_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '4 этаж(по главной лестнице)', callback_data: 'navigation_4_main_stage_old')
      FOURTH_RIGHT_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '4 этаж(правое крыло)', callback_data: 'navigation_4_right_stage_old')
      FIFTH_STAGE_OLD ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: '5 этаж(правое крыло)', callback_data: 'navigation_5_stage_old')
      TRANSITION ||= Telegram::Bot::Types::InlineKeyboardButton.new(text: 'Переход', callback_data: 'navigation_transition')
    end
  end
end
