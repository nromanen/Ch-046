<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script>document.write('<base href="' + document.location + '" />');</script>
    <title>HMAC Security example </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="icon" href="images/favicon.ico">

    <!-- Polyfill(s) for older browsers -->
    <script src="node_modules/core-js/client/shim.min.js"></script>
    <script src="node_modules/zone.js/dist/zone.js"></script>
    <script src="node_modules/reflect-metadata/Reflect.js"></script>
    <script src="node_modules/systemjs/dist/system.src.js"></script>

    <script src="node_modules/jquery/dist/jquery.min.js"></script>
    <script src="node_modules/lodash/lodash.js"></script>
    <script src="node_modules/crypto-js/crypto-js.js"></script>
    <script src="node_modules/crypto-js/hmac-sha256.js"></script>
    <script src="node_modules/crypto-js/hmac-sha1.js"></script>
    <script src="node_modules/crypto-js/hmac-md5.js"></script>

    <!-- Initialization -->
    <script src="js/systemjs.config.js"></script>
    <script src="js/init.js"></script>
</head>
<body>
    <hmac-app>LOADING ... </hmac-app>
</body>
</html>