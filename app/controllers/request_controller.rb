class RequestController < ApplicationController
	def index
		parser = Parser.new
		string = params["loc"]
		string = URI.encode(string)
		@json = parser.generate_itinerary_json(string)
	end
end
