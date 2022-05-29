module Bot
  class Proposal
    attr_accessor :type, :additional_info, :inline_button, :callback_data

    def initialize(type, button, additional_info = '')
      @type = type
      @additional_info = additional_info
      @inline_button = button[0]
      @callback_data = button[1]
    end
  end
end
