json.array!(@messages) do |message|
  json.extract! message, :id, :text, :to, :from
  json.url message_url(message, format: :json)
end