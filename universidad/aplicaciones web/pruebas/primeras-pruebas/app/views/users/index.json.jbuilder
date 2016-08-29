json.array!(@users) do |user|
  json.extract! user, :id, :name, :token, :number, :email
  json.url user_url(user, format: :json)
end
