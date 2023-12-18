-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 14 déc. 2023 à 01:11
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `scraping`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien_immobilier`
--

CREATE TABLE `bien_immobilier` (
  `idBien` int(11) NOT NULL,
  `titreBien` varchar(255) NOT NULL,
  `prixBien` decimal(10,2) NOT NULL,
  `superficieBien` decimal(10,2) NOT NULL,
  `photoBien` varchar(255) DEFAULT NULL,
  `descriptionBien` text DEFAULT NULL,
  `siteBien` varchar(255) DEFAULT NULL,
  `idTypeBien` int(11) DEFAULT NULL,
  `idLocalisationBien` int(11) DEFAULT NULL,
  `urlAnnonce` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `bien_immobilier`
--

INSERT INTO `bien_immobilier` (`idBien`, `titreBien`, `prixBien`, `superficieBien`, `photoBien`, `descriptionBien`, `siteBien`, `idTypeBien`, `idLocalisationBien`, `urlAnnonce`) VALUES
(379, 'Lorient 56', 723958.00, 146.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17477096_1_1702432889_cdabb109b3ec5ea6552bd93495a5fb9f_rcrop_480-360_.jpg', 'Vente maison 7 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17477096.htm'),
(380, 'Lorient 56', 232700.00, 65.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17477020_1_1702432209_371e78443957dd866dc7c42551c4aa58_rcrop_480-360_.jpg', 'Vente maison 3 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17477020.htm'),
(381, 'Lorient Kerentrech - Keryado 56', 345000.00, 114.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-kerentrech-keryado-56/207/maison-a-vendre-lorient-kerentrech-keryado-17476546_1_1702429561_02421b83edc4b2332ff0b12a7614bbcd_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17476546.htm'),
(382, 'Lorient 56', 494550.00, 150.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17462282_1_1701960902_b67c6622ed5c7ab722de4f2bb4ca7f9b_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17462282.htm'),
(383, 'Lorient 56', 410000.00, 115.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17412768_1_1700431094_23e2c72af12554766af1eed672159b69_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17412768.htm'),
(384, 'Lorient 56', 249500.00, 75.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17395592_1_1699907701_507f2b1f1bff3982c3b205eac2f972f6_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17395592.htm'),
(385, 'Lorient Kerentrech - Keryado 56', 275600.00, 90.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-kerentrech-keryado-56/207/maison-a-vendre-lorient-kerentrech-keryado-17395376_1_1699903982_295ba5b9363d9d58ef99f0488b89e892_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17395376.htm'),
(386, 'Lorient Merville - Polygone 56', 674900.00, 150.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-merville-polygone-56/207/maison-a-vendre-lorient-merville-polygone-17378405_1_1699387862_f354ec8a7943f4adce6aec6a7ac997aa_rcrop_480-360_.jpg', 'Vente maison 7 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17378405.htm'),
(387, 'Lorient 56', 509200.00, 150.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-56/207/maison-a-vendre-lorient-17363614_1_1698800904_fea790dc808aaa13167ba2e177273aa0_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17363614.htm'),
(388, 'Lorient Centre Ville 56', 249100.00, 115.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-centre-ville-56/207/maison-a-vendre-lorient-centre-ville-17335373_1_1697829841_5bf965db2fd9abb0e4ff4951aab6bac2_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17335373.htm'),
(389, 'Lorient Centre Ville 56', 305000.00, 91.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-centre-ville-56/207/maison-a-vendre-lorient-centre-ville-17334861_1_1697818981_c466db9ea78a3245ad49b090ad2e2226_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17334861.htm'),
(390, 'Lorient Merville - Polygone 56', 373000.00, 122.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-merville-polygone-56/207/maison-a-vendre-lorient-merville-polygone-17255044_1_1695747615_514ed96c80c8d961ed4e2b5f4521ba01_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17255044.htm'),
(391, 'Lorient Kervenanec - Le Ter 56', 143550.00, 71.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-lorient-kervenanec-le-ter-56/207/maison-a-vendre-lorient-kervenanec-le-ter-17252636_1_1695682338_7034434f134fd632f0b2f14336b2db45_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 2, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/lorient-56-56121/17252636.htm'),
(392, 'Vannes Centre Ville 56', 507945.00, 85.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17479765_1_1702506615_ab440ce353e4610a517172c5b86be786_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17479765.htm'),
(393, 'Vannes 56', 659115.00, 180.00, '/pic/bouchetrou_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17479757.htm'),
(394, 'Vannes 56', 331940.00, 104.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17478645_1_1702468682_da035b51faf94846db805d6320d067fc_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17478645.htm'),
(395, 'Vannes Centre Ville 56', 840000.00, 153.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17477599_1_1702440904_3ae6d5a5c1df09139d8e164b9ebd86cb_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17477599.htm'),
(396, 'Vannes 56', 362250.00, 64.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17476478_1_1702423202_df272409485dde4fb03e6c5800886bb1_rcrop_480-360_.jpg', 'Vente maison', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17476478.htm'),
(397, 'Vannes 56', 606524.00, 117.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17463851_1_1701985938_cf21f5a5e764f448576693938599cdcd_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17463851.htm'),
(398, 'Vannes 56', 468000.00, 190.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17462302_1_1701960978_10d8d2045625536449101812d42451e0_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17462302.htm'),
(399, 'Vannes 56', 357000.00, 71.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17460854_1_1701918105_bd36325aacbcc8d47f56f0b3073818b9_rcrop_480-360_.jpg', 'Vente maison 3 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17460854.htm'),
(400, 'Vannes Centre Ville 56', 423200.00, 86.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17447154_1_1701458763_f1a668d8621b25d7bc9bf3be9424903e_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17447154.htm'),
(401, 'Vannes Centre Ville 56', 338000.00, 100.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17445704_1_1701423371_1682b3ee998ff1d453aadde77f8ee8fb_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17445704.htm'),
(402, 'Vannes Gare - Hôpital 56', 516000.00, 130.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-gare-hopital-56/207/maison-a-vendre-vannes-gare-hopital-17423474_1_1700757330_f6a76b682e7a79f03c1a03459dbf016e_rcrop_480-360_.jpg', 'Vente maison', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17423474.htm'),
(403, 'Vannes Ménimur - St-Guen 56', 391400.00, 94.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-menimur-st-guen-56/207/maison-a-vendre-vannes-menimur-st-guen-17396025_1_1699913223_55e873de4aa888d763566bcd071724c6_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17396025.htm'),
(404, 'Vannes 56', 819000.00, 121.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17377330_1_1699362688_74b72db4d9e9e5d140341a3eff59f4de_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17377330.htm'),
(405, 'Vannes 56', 272000.00, 85.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17376818_1_da147039c1b3821438e02c1bd5f2f2ef_rcrop_480-360_.jpeg', 'Vente maison', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17376818.htm'),
(406, 'Vannes Ménimur - St-Guen 56', 445000.00, 156.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-menimur-st-guen-56/207/maison-a-vendre-vannes-menimur-st-guen-17376456_1_343c86552746db41b45a8f008e4d503f_rcrop_480-360_.jpeg', 'Vente maison 7 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17376456.htm'),
(407, 'Vannes Vincin 56', 340000.00, 85.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-vincin-56/207/maison-a-vendre-vannes-vincin-17374660_1_5875ae86b490e653374ff08e43457da0_rcrop_480-360_.jpeg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17374660.htm'),
(408, 'Vannes Centre Ville 56', 276671.00, 86.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17354046_1_1702286300_638094a8aadc412f0ec8a03ae5101f42_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17354046.htm'),
(409, 'Vannes Centre Ville 56', 469200.00, 102.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17350725_1_1698345964_29541bf29e30c7f7f301fa4578f7d703_rcrop_480-360_.jpg', 'Vente maison 5 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17350725.htm'),
(410, 'Vannes Centre Ville 56', 1133000.00, 150.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17350703_3_1698345484_f2129deebd0bbb5c64e197aeb2543765_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17350703.htm'),
(411, 'Vannes Centre Ville 56', 618000.00, 117.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-centre-ville-56/207/maison-a-vendre-vannes-centre-ville-17350498_4_1698340444_8ec7a92a9b3fabd08abbc35f0a117a0e_rcrop_480-360_.jpg', 'Vente maison 6 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17350498.htm'),
(412, 'Vannes Nord-est 56', 425000.00, 86.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-nord-est-56/207/maison-a-vendre-vannes-nord-est-17334959_1_1702473393_87a1c541cdce83665034e86b622ae268_rcrop_480-360_.jpg', 'Vente maison 4 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17334959.htm'),
(413, 'Vannes Kercado - Trussac 56', 895000.00, 167.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-kercado-trussac-56/207/maison-a-vendre-vannes-kercado-trussac-17334480_3_1697809504_9b6ecf88ba8f44e2da9cb8ebee0cc05a_rcrop_480-360_.jpg', 'Vente maison 8 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17334480.htm'),
(414, 'Vannes 56', 985000.00, 155.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17333912_1_1697797325_bcb5cffc811cd92c59a39d240ae651af_rcrop_480-360_.jpg', 'Vente maison 8 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17333912.htm'),
(415, 'Vannes 56', 630000.00, 151.00, 'https://www.ouestfrance-immo.com/photo-vente-maison-vannes-56/207/maison-a-vendre-vannes-17309684_2_1697294402_1d4c4dc1a2e15c401983369a81a94702_rcrop_480-360_.jpg', 'Vente maison 7 pièces', 'URL du site', 1, 1, 'https://www.ouestfrance-immo.com/immobilier/vente/maison/vannes-56-56260/17309684.htm');

-- --------------------------------------------------------

--
-- Structure de la table `localisation_bien_immobilier`
--

CREATE TABLE `localisation_bien_immobilier` (
  `idLocalisationBien` int(11) NOT NULL,
  `localisationBien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `localisation_bien_immobilier`
--

INSERT INTO `localisation_bien_immobilier` (`idLocalisationBien`, `localisationBien`) VALUES
(1, 'Vannes'),
(2, 'Lorient'),
(3, 'Quimper'),
(4, 'Brest'),
(5, 'Rennes'),
(6, 'Saint-Brieuc');

-- --------------------------------------------------------

--
-- Structure de la table `type_bien_immobilier`
--

CREATE TABLE `type_bien_immobilier` (
  `idTypeBien` int(11) NOT NULL,
  `typeBien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `type_bien_immobilier`
--

INSERT INTO `type_bien_immobilier` (`idTypeBien`, `typeBien`) VALUES
(1, 'maison'),
(2, 'manoir'),
(3, 'parking'),
(4, 'immeuble');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bien_immobilier`
--
ALTER TABLE `bien_immobilier`
  ADD PRIMARY KEY (`idBien`),
  ADD KEY `idTypeBien` (`idTypeBien`),
  ADD KEY `idLocalisationBien` (`idLocalisationBien`);

--
-- Index pour la table `localisation_bien_immobilier`
--
ALTER TABLE `localisation_bien_immobilier`
  ADD PRIMARY KEY (`idLocalisationBien`);

--
-- Index pour la table `type_bien_immobilier`
--
ALTER TABLE `type_bien_immobilier`
  ADD PRIMARY KEY (`idTypeBien`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bien_immobilier`
--
ALTER TABLE `bien_immobilier`
  MODIFY `idBien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=416;

--
-- AUTO_INCREMENT pour la table `localisation_bien_immobilier`
--
ALTER TABLE `localisation_bien_immobilier`
  MODIFY `idLocalisationBien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `type_bien_immobilier`
--
ALTER TABLE `type_bien_immobilier`
  MODIFY `idTypeBien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien_immobilier`
--
ALTER TABLE `bien_immobilier`
  ADD CONSTRAINT `bien_immobilier_ibfk_1` FOREIGN KEY (`idTypeBien`) REFERENCES `type_bien_immobilier` (`idTypeBien`),
  ADD CONSTRAINT `bien_immobilier_ibfk_2` FOREIGN KEY (`idLocalisationBien`) REFERENCES `localisation_bien_immobilier` (`idLocalisationBien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
