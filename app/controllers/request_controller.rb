class RequestController < ApplicationController
	def index
		parser = Parser.new
		string = params["loc"]
		logger.debug "This is string: #{string}"
		string = URI.encode(string)
		@json = parser.generate_itinerary_json(string)
		respond_to do |format|
			format.json { render json: @json, status: :created}
		end
	end
end
