<%@ tag pageEncoding="UTF-8" %>


<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>

    </ol>


    <div class="container">

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img style="margin: auto" src="../../assets/img/carosel1.jpg" alt="Chania">
            </div>

            <div class="item">
                <img style="margin: auto" src="../../assets/img/carosel2.jpg" alt="Chania">
            </div>

            <div class="item">
                <img style="margin: auto" src="../../assets/img/carosel3.jpg" alt="Flower">
            </div>

            <div class="item">
                <img style="margin: auto" src="../../assets/img/carosel4.jpg" alt="Flower">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<div class="container">