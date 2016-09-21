json.array!(@texts) do |text|
  json.extract! text, :id, :to, :from, :text, :token
  json.url text_url(text, format: :json)
end
