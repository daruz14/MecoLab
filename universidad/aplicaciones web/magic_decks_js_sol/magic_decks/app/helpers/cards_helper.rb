module CardsHelper
  def mana_cost_to_images(text)
    if text.nil? || text.blank?
      "-"
    else
      replacements = {
        '{W}' => image_tag('mana_white.png'),
        '{U}' => image_tag('mana_blue.png'),
        '{B}' => image_tag('mana_black.png'),
        '{R}' => image_tag('mana_red.png'),
        '{G}' => image_tag('mana_green.png'),
        '{C}' => image_tag('mana_colorless.png'),
      }
      (0..9).each do |number|
        replacements["{#{number}}"] = image_tag("mana_#{number}.png")
      end
      text.gsub /\{.\}/, replacements
    end
  end
end
