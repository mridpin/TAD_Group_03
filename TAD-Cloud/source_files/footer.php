<?php
if (strpos($_SERVER['PHP_SELF'], "account.php") !== false) {
    ?>
    <footer class='w3-margin-top w3-container w3-teal w3-text-white w3-right-align' style='box-shadow: 0px 500px 0px 500px #1a7c78; clear: both;' >       
        <h3><em>&copy;PCGalaxyNova</em></h3>
        <h4>TAD Grupo 03:</h4>
        <ul>
            <li>
                Manuel Ridao Pineda
            </li>
            <li>
                Antonio Jos&eacute; Herrera Tabaco
            </li>
            <li>
                Juan Antonio Rodr&iacute;guez Rodr&iacute;guez
            </li>
        </ul>
    </footer>
    <?php
} else {
    ?>
    <footer class='w3-margin-top w3-container w3-teal w3-text-white' style='box-shadow: 0px 500px 0px 500px #1a7c78; clear: both;' >        
        <h3><em>&copy;PCGalaxyNova</em></h3>
        <h4>TAD Grupo 03:</h4>
        <ul>
            <li>
                Manuel Ridao Pineda
            </li>
            <li>
                Antonio Jos&eacute; Herrera Tabaco
            </li>
            <li>
                Juan Antonio Rodr&iacute;guez Rodr&iacute;guez
            </li>
        </ul>
    </footer>
    <?php
}
?>