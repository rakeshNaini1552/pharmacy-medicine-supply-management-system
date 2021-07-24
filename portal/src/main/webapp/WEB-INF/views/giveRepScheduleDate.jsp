<%@ include file="common/header.jspf"%>

<article>

	<h1 class="text-center p-5">Medical Representatives Schedule</h1>

	<div class="row">
		<div class="col">
		</div>
		<div class="col card bg-light text-center" style="width: 18rem;">
			<div class="card-body">
				<h5 class="card-title">Select Date</h5>
				<p class="card-text">To view the schedule of the representatives
				</p>


				<form action="/user/RepSchedule" method="post">

					<div style="color: red; margin: 20px">
						<c:choose>
							<c:when test="${errorMessage}">
							Please Enter today's / upcoming date
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>

					</div>


					<div style="color: red"></div>
					<div>
						<div>
							 <span style="padding: 5px;">Date of meeting</span> <input type="date" name="scheduleStartDate"
								value="" required="true">
						</div>
					</div>
					<div>
						<div>
							<input type="submit" class="btn btn-info m-3" name="submit"
								value="View schedule">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col">
			
		</div>
	</div>

</article>

<%@ include file="common/footer.jspf"%>