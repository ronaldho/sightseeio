require 'oauth'
require 'json'

class Parser

  def initialize()
    consumer_key = 'byebZmVlhZe12Wxk3Cw9ww'
    consumer_secret = 'XGft9CBUdoeNHetks3RNE_akijE'
    token = '9lZLtGrz_lknuoOvfI4G8mBVdfgUiaHV'
    token_secret = 'GLlClpbLXSNPNHtxAFCPVDV1Rp4'
    api_host = 'api.yelp.com'
    consumer = OAuth::Consumer.new(consumer_key, consumer_secret, {:site => "http://#{api_host}"})
    @access_token = OAuth::AccessToken.new(consumer, token, token_secret)
  end

  private
  def create_item(tm, loc)
  	if !tm.nil? and !loc.nil?
    	path = "/v2/search?term=#{tm.gsub ' ', '%20'}&location=#{loc.gsub ' ','%20'}&limit=20&sort=0"
		#path = URI.encode(path)
    
    else
    	path = "/v2/search?term=#{tm}&location=#{loc}&limit=20&sort=0"
    end
    
	yelp_data = JSON.parse(@access_token.get(path).body)
	
	#@access_token.get(path).body
    Hash[name: yelp_data['businesses'][Random.rand(5)]['name'], rating: yelp_data['businesses'][Random.rand(5)]['rating'], location: yelp_data['businesses'][Random.rand(5)]['location']['display_address']]
  end

  public
  def generate_itinerary_json(loc)
    itinerary = Hash[:businesses => []]
    itinerary[:businesses] << create_item('breakfast',loc)
    itinerary[:businesses] << create_item('point of interest',loc)
    itinerary[:businesses] << create_item('lunch', loc)
    itinerary[:businesses] << create_item('point of interest',loc)
    itinerary[:businesses] << create_item('dinner', loc)
    itinerary.to_json
  end
end

parser = Parser.new
puts parser.generate_itinerary_json("University of Washington")