class RequestController < ApplicationController
	def index
		parser = Parser.new
		string = params["loc"]
		logger.debug "This is string: #{string}"
		string = URI.encode(string)
		@json = parser.generate_itinerary_json(string)
	end
end
