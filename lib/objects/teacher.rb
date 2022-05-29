module Bot
  class Teacher
    attr_accessor :id, :full_name, :email, :phone, :positions, :inline_button, :callback_data

    def initialize(hash, button = [])
      @id = hash['id']
      @full_name = hash['fullName']
      @positions = []
    end
  end
end
