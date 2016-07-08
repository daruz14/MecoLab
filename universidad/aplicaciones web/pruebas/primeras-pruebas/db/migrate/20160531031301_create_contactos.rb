class CreateContactos < ActiveRecord::Migration
  def change
    create_table :contactos do |t|
      t.string :Nombre
      t.string :Apellido
      t.integer :Telefono

      t.timestamps null: false
    end
  end
end
