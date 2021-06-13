<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jspf"%>


<!-- Search Section Starts -->
<section class="search-area condensed parallax">
	<!-- Nested Container Starts -->
	<div class="container text-center">
		<h3 class="text-weight-normal">Find the best Restaurants, Cafes
			&amp; Cuisine in Your Place</h3>
		<form class="top-search">
			<div class="input-group">
				<div class="input-group-prepend search-panel">
					<button type="button"
						class="btn btn-lg btn-default dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="fa fa-map-marker"></span> <span class="text-label">Please
							type location</span>
					</button>
					<!-- 위치변경 삭제 -->
					<ul class="dropdown-menu rounded-0">
						<a href="#" class="dropdown-item">All of Texas</a>
						<a href="#" class="dropdown-item lead text-uppercase">Popular
							Places</a>
						<a href="#" class="dropdown-item">Huston, Texas</a>
						<a href="#" class="dropdown-item">San Antonio, Texas</a>
						<a href="#" class="dropdown-item">Galveston, Texas</a>
						<a href="#" class="dropdown-item">Corpus Christi, Texas</a>
					</ul>
				</div>
				<input type="text" class="form-control input-lg rounded-0"
					name="search-location"
					placeholder="Search for a Restaurants, Cafes, Cuisine, etc..,">
				<button class="btn btn-lg btn-prime animation text-uppercase"
					type="button">Search</button>
			</div>
		</form>
	</div>
	<!-- Nested Container Ends -->
</section>
<!-- Search Section Ends -->
<!-- BreadCrumb Starts -->
<div class="breadcrumb rounded-0">
	<!-- Nested Container Starts -->
	<div class="container text-xs-center text-sm-center text-md-left">
		<ul class="list-unstyled list-inline">
			<li class="list-inline-item"><a href="index.html">Home</a></li>
			<li class="list-inline-item"><a href="#">Restaurants in
					jacksonville</a></li>
			<li class="active list-inline-item">Jack Hills</li>
		</ul>
	</div>
	<!-- Nested Container Ends -->
</div>
<!-- BreadCrumb Ends -->
<!-- Main Container Starts -->
<div class="main-container container">
	<!-- Heading Starts -->
	<h2 class="main-heading-1 text-xs-center text-sm-center text-md-center">
		게 시 판</h2>
	<!-- Heading Ends -->
	<!-- Starts -->
	<div class="row">
		<!-- Sidearea Starts -->
		<div class="col-lg-3 col-md-4 col-sm-12">
			<!-- Sidearea Filters Starts -->
			<div class="sidearea-filter">
				<!-- Search Field Starts -->
				<div class="input-group sidearea-filter-search">
					<input type="text" class="form-control rounded-0"
						placeholder="Search for..."> <span
						class="input-group-append">
						<button class="btn btn-default rounded-0" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<!-- Search Field Ends -->
				<!-- Sort By Field Starts -->
				<select class="form-control rounded-0 sidearea-filter-sort">
					<option>Sort by : 평점높은순</option>
					<option>Top Rated</option>
					<option>Latest</option>
					<option>Cost</option>
					<option>Cheap</option>
				</select>
				<!-- Sort By Field Ends -->
			</div>
			<!-- Sidearea Filters Ends -->
		</div>
		<!-- Sidearea Ends -->
		<!-- Mainarea Starts -->
		<div class="col-lg-9 col-md-8 col-sm-12">
			<!-- Hotels List Starts -->
			<div class="hotels-list text-xs-center text-sm-center text-lg-left">
				<!-- List #1 Starts -->
				<div class="list-box clearfix">
					<div class="container">
						<div class="row">
							<table class="table table-hover"
								style="text-align: center; border: 1px solid #dddddd">
								<thead>

									<tr>
										<th style="background-color: #eeeeee; text-align: center;">번호</th>
										<th style="background-color: #eeeeee; text-align: center;">제목</th>
										<th style="background-color: #eeeeee; text-align: center;">작성자</th>
										<th style="background-color: #eeeeee; text-align: center;">작성일</th>
										<th style="background-color: #eeeeee; text-align: center;">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="n" items="#" begin="0" end="4"
										varStatus="st">
										<tr>
											<td>"#"</td>
											<td class="title indent text-dlign-left"><a
												href="detail?id=#">"#"</a></td>
											<td>"#"</td>
											<td>"#"</td>
											<td>"#"</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div style="margin: 0 auto;">
								<ul class="pagination animation float-lg-right">
									<c:set var="page" value="${(param.p==null)?1:param.p}" />
									<c:set var="startNum" value="${page-(page-1)%5}" />
									<c:set var="lastNum" value="23" />
									<c:if test="${startNum>1}">
										<li class="page-item"><a href="?p=${startNum-5}&t&q="
											class="page-link">&laquo;</a></li>
									</c:if>
									<c:if test="${startNum<=1}">
										<li class="page-item"><a
											onclick="alert('이전 페이지가 없습니다.');" class="page-link">&laquo;</a></li>
									</c:if>

									<c:forEach var="i" begin="0" end="4">
										<li class="page-item active"><a
											href="?p=${startNum+i}&t=&q=" class="page-link">&nbsp;${startNum+i}&nbsp;</a></li>
										<%-- <li class="page-item"><a href="?p=${startNum+i}&t=&q="
						class="page-link">&nbsp;${startNum+i}&nbsp;</a></li>
					<li class="page-item"><a href="?p=${startNum+i}&t=&q="
						class="page-link">&nbsp;${startNum+i}&nbsp;</a></li>
					<li class="page-item"><a href="?p=${startNum+i}&t=&q="
						class="page-link">&nbsp;${startNum+i}&nbsp;</a></li>
					<li class="page-item"><a href="?p=${startNum+i}&t=&q="
						class="page-link">&nbsp;${startNum+i}&nbsp;</a></li> --%>
									</c:forEach>

									<c:if test="${startNum+5<lastNum}">
										<li class="page-item"><a href="?p=${startNum+5}&t&q="
											class="page-link">&raquo;</a></li>
									</c:if>
									<c:if test="${startNum+5>lastNum}">
										<li class="page-item"><a
											onclick="alert('다음 페이지가 없습니다.');" class="page-link">&raquo;</a></li>
									</c:if>

								</ul>
							</div>
							<div>
								<a style="float: right;" href="write.jsp"
									class="btn btn-primary" style="margin-bottom: 14px;">글쓰기</a>
							</div>
						</div>
					</div>
				</div>
				<!-- List Right Col Ends -->
			</div>
			<!-- List #6 Ends -->
		</div>
		<!-- Hotels List Ends -->
	</div>
	<!-- Mainarea Ends -->
</div>
<!-- Ends -->
<!-- Main Container Ends -->
<!-- Change Location Modal Window Starts -->
<div class="modal fade" id="change-location" tabindex="-1" role="dialog"
	aria-labelledby="change-location">
	<div class="modal-dialog" role="document">
		<!-- Modal Content Starts -->
		<div class="modal-content">
			<!-- Modal Header Starts -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Choose your location in Jacksonville</h4>
			</div>
			<!-- Modal Header Ends -->
			<!-- Modal Body Starts -->
			<div class="modal-body">
				<!-- Nested Container Starts -->
				<div class="container-fluid">
					<h4>Jacksonville City</h4>
					<div class="row"></div>
				</div>
				<!-- Nested Container Ends -->
			</div>
			<!-- Modal Body Ends -->
		</div>
		<!-- Modal Content Ends -->
	</div>
</div>
<!-- Change Location Modal Window Ends -->


<%@ include file="../include/header.jspf"%>