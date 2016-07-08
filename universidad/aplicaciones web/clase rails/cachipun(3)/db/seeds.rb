# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)
if User.first.nil?
  # no hay usuarios
  names = ["Fernando", "Camilo", "Diego", "Felipe", "Marco", "Rafael", "Manuel", "Diego", "Gabriel", "Javier", "Patricio", "Javier", "Antonio", "Tomas", "Nicolas", "Antonio", "Agustin", "Vicente", "Matías", "Gerardo", "Bastián", "Gerardo", "Hector", "Daniela", "Felipe", "David", "Rodrigo", "Fernando", "Cristobal", "Jorge", "Felix", "Maria", "Diego", "Fabio", "Juan"]
  names.uniq.map{|n| n.downcase }.each do |name|
    User.create(username: name, password: '12345678')
  end
end
