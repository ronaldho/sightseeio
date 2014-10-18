require 'oauth'
require 'json'

class Parser

  def initialize()
    consumer_key = 'byebZmVlhZe12Wxk3Cw9ww'
    consumer_secret = 'XGft9CBUdoeNHetks3RNE_akijE'
    token = 'E_hTMDH1N1pway_ZHzhk9yx5synssvdZ'
    token_secret = 'zbkyOqQ8KFslDGw9dOIF8IB99xs'
    api_host = 'api.yelp.com'
    consumer = OAuth::Consumer.new(consumer_key, consumer_secret, {:site => "http://#{api_host}"})
    @access_token = OAuth::AccessToken.new(consumer, token, token_secret)
  end

  private
  def create_item(tm, loc, at=@access_token)
  	if !tm.nil? and !loc.nil?
    	path = "/v2/search?term=#{tm.gsub ' ', '%20'}&location=#{loc.gsub ' ','%20'}&limit=20&sort=0"
		#path = URI.encode(path)
    
    else
    	path = "/v2/search?term=#{tm}&location=#{loc}&limit=20&sort=0"
    end
    
	yelp_data = JSON.parse(at.get(path).body)
	#puts yelp_data['businesses']
	
    Hash[name: yelp_data['businesses'][Random.rand(21)]['name'], rating: yelp_data['businesses'][Random.rand(21)]['rating'], location: yelp_data['businesses'][Random.rand(21)]['location']['display_address']]
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