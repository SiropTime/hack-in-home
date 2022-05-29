module Bot
  class Subject
    attr_accessor :id, :name, :url, :module_start_date, :module_end_date, :teacher_id, :inline_button, :callback_data

    def initialize(hash, button)
      @id = hash['id']
      @name = hash['name']
      @url = hash['url']
      @module_start_date = hash['moduleStartDate']
      @module_end_date = hash['moduleEndDate']
      @teacher_id = hash['teacherId']
      @inline_button = button[0]
      @callback_data = button[1]
    end
  end
end
