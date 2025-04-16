-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : mer. 16 avr. 2025 à 11:24
-- Version du serveur : 8.0.40
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `recrutement`
--

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE `candidat` (
  `id` int NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(100) DEFAULT NULL,
  `competences` text,
  `experience` text,
  `sexe` tinyint(1) DEFAULT NULL,
  `region` enum('Nord','Sud','Est','Ouest') DEFAULT NULL,
  `trancheAge` enum('moins de 18','18-30','30-50','50+') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `candidat`
--

INSERT INTO `candidat` (`id`, `nom`, `email`, `motDePasse`, `competences`, `experience`, `sexe`, `region`, `trancheAge`) VALUES
(6, 'poiu', 'poiu', 'poiu', '', '', 0, 'Nord', 'moins de 18'),
(2, 'test2', 'test2', 'test2', '', '', 0, 'Sud', '30-50'),
(3, 'qsdf', 'qsdf', 'qsdf', '', '', 0, 'Sud', '18-30'),
(4, 'fdsq', 'fdsq', 'fdsq', '', '', 1, 'Ouest', '50+'),
(5, 'mlkj', 'mlkj', 'mlkj', '', '', 1, 'Ouest', '30-50');

-- --------------------------------------------------------

--
-- Structure de la table `Candidature`
--

CREATE TABLE `Candidature` (
  `id` int NOT NULL,
  `idCandidat` int DEFAULT NULL,
  `idOffre` int DEFAULT NULL,
  `statut` varchar(50) DEFAULT NULL,
  `lettreMotivation` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Candidature`
--

INSERT INTO `Candidature` (`id`, `idCandidat`, `idOffre`, `statut`, `lettreMotivation`) VALUES
(1, 1, 1, 'en attente', NULL),
(2, 1, 1, 'en attente', NULL),
(3, 1, 2, 'en attente', NULL),
(4, 3, 1, 'en attente', NULL),
(5, 4, 2, 'en attente', NULL),
(6, 4, 1, 'en attente', NULL),
(7, 3, 1, 'En attente', 'qsdfqsdf');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `id` int NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `description` text,
  `competencesRequises` text,
  `statut` varchar(50) DEFAULT NULL,
  `recruteurId` int DEFAULT NULL,
  `categorie` enum('Direction','Ingénierie','Marketing','Finance','RH') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`id`, `titre`, `description`, `competencesRequises`, `statut`, `recruteurId`, `categorie`) VALUES
(1, '', '', '', 'Ouvert', 2, NULL),
(2, 'qsdfqsdf', 'qsdfqsdf', 'fdqsqsdf', 'Ouvert', 2, NULL),
(3, '', '', '', 'Ouvert', 1, NULL),
(4, 'qsdffdsq', 'qsdffdsq', 'qsdffdsq', 'Ouvert', 2, 'Marketing');

-- --------------------------------------------------------

--
-- Structure de la table `recruteur`
--

CREATE TABLE `recruteur` (
  `id` int NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `recruteur`
--

INSERT INTO `recruteur` (`id`, `nom`, `email`, `motDePasse`) VALUES
(1, 'julien', 'julien', 'julien'),
(2, 'julien1', 'julien1', 'julien1');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `motDePasse` varchar(100) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `Candidature`
--
ALTER TABLE `Candidature`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCandidat` (`idCandidat`),
  ADD KEY `idOffre` (`idOffre`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recruteurId` (`recruteurId`);

--
-- Index pour la table `recruteur`
--
ALTER TABLE `recruteur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `candidat`
--
ALTER TABLE `candidat`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `Candidature`
--
ALTER TABLE `Candidature`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `recruteur`
--
ALTER TABLE `recruteur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
