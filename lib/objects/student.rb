module Bot
  class Student
    attr_accessor :name, :middle_name, :surname, :email, :group

    def initialize(hash)
      @name = hash['name']
      @middle_name = hash['middlename']
      @surname = hash['surname']
      @email = hash['email']
      @group = hash['groupId']['name']
    end
  end
end
