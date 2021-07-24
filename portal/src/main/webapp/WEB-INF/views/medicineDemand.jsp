

<%@ include file="common/header.jspf"%>

<article>
	<div class="container">
		<h1 class="text-center m-4">Place Order</h1>

		<div class="w-50 p-3 mx-auto border bg-light rounded text-center">
			<div class="card-body">
				<h3 class="card-title">Enter details</h3>
			</div>
			<div>
				<form action="/user/PharmacySupply" method="post">
					<div class="form-group row">
						<label for="medicineName" class="col-sm-3 col-form-label">Medicine</label>
						<div class="col-sm-9">



							<select id="medicineName" name="medicineName" style="float: left">
								<option value="Panif">Panif</option>
								<option value="Nexpro">Nexpro</option>
								<option value="Vitamin-c">Vitamin-c</option>
								<option value="Dolo-650">Dolo-650</option>
								<option value="Orthoherb">Orthoherb</option>
								<option value="Crocin">Crocin</option>
								<option value="Cholecalciferol">Cholecalciferol</option>
								<option value="Digoxin">Digoxin</option>
								<option value="Ascoril">Ascoril</option>
								<option value="Hilact">Hilact</option>
								<option value="Cyclopam">Cyclopam</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label for="demandCount" class="col-sm-3 col-form-label">Demand
							Count</label>
						<div class="col-sm-9">
							<input type="number" class="form-control" id="demandCount"
								name="demandCount" placeholder="Enter value" min="1"
								required="required">
						</div>
					</div>

					<input type="submit" class="btn btn-primary" name="submit"
						value="Submit">
				</form>
			</div>
		</div>

	</div>
</article>

<%@ include file="common/footer.jspf"%>






























<!-- onsubmit="return validate(); -->