# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160118182241) do

  create_table "mtg_card_types", force: :cascade do |t|
    t.integer  "mtg_card_id"
    t.integer  "mtg_type_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "mtg_cards", force: :cascade do |t|
    t.string   "name"
    t.string   "gatherer_url"
    t.string   "multiverse_id"
    t.string   "gatherer_image_url"
    t.string   "mana_cost"
    t.string   "converted_cost"
    t.text     "oracle_text"
    t.text     "flavor_text"
    t.string   "mark"
    t.string   "power"
    t.string   "toughness"
    t.string   "loyalty"
    t.string   "rarity"
    t.string   "transformed_id"
    t.string   "colors"
    t.string   "artist"
    t.integer  "mtg_set_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "mtg_sets", force: :cascade do |t|
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "mtg_types", force: :cascade do |t|
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
