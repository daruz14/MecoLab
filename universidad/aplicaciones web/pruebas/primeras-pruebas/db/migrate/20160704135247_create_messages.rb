class CreateMessages < ActiveRecord::Migration
  def change
    create_table :messages do |t|
      t.string :text
      t.string :to
      t.string :from

      t.timestamps null: false
    end
  end
end
