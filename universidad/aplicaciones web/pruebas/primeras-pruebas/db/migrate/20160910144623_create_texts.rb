class CreateTexts < ActiveRecord::Migration
  def change
    create_table :texts do |t|
      t.string :to
      t.string :from
      t.string :text
      t.string :token

      t.timestamps null: false
    end
  end
end
