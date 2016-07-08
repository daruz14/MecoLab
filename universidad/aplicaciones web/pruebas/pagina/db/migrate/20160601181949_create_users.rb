class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :nombre
      t.string :apellido
      t.integer :telefono

      t.timestamps null: false
    end
  end
end
