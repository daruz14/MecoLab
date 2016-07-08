class CreateMessages < ActiveRecord::Migration
  def change
    create_table :messages do |t|
      t.string :author, null: false
      t.string :message, null: false
      t.integer :timestamp, null: false, limit: 16
      t.datetime :send_at, null: false

      t.timestamps null: false
    end
  end
end
