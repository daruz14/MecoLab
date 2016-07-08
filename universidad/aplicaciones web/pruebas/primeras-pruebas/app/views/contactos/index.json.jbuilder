json.array!(@contactos) do |contacto|
  json.extract! contacto, :id, :Nombre, :Apellido, :Telefono
  json.url contacto_url(contacto, format: :json)
end
