<div id="top"></div>

<!-- TITLE -->
<div align="center">
<h1 align="center">Jandas</h1>

  <p align="center">
    This project is a java adaptation of the panda library. Thus the name Jandas. 
  </p>

<a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://github.com/MathysC/Jandas/actions/workflows/CI.yml/badge.svg?branch=dev" alt="CI"></a> <a href="https://github.com/MathysC/Jandas/actions/workflows/CD.yml"><img src="https://github.com/MathysC/Jandas/actions/workflows/CD.yml/badge.svg?branch=dev" alt="CD"></a><a href="https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml"><img src="https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml/badge.svg?branch=dev" alt="Deploy github pages"></a> <a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://mathysc.github.io/Jandas/docs/badges/jacoco.svg" alt="CI"></a>  <a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://mathysc.github.io/Jandas/docs/badges/branches.svg" alt="Branches"></a> <a href="https://mathysc.github.io/Jandas/docs/javadoc"><img src="https://img.shields.io/badge/-javadoc-yellow" alt="Javadoc"></a> <a href="https://mathysc.github.io/Jandas/docs/jacoco"><img src="https://img.shields.io/badge/-jacoco-red" alt="Jacoco"></a> <a href="http://www.gnu.org/licenses/gpl-3.0"><img src="https://img.shields.io/badge/License-GPL%20v3-blue.svg" alt="License: GPL v3"></a>
<p align="center"> Click on a badge for more information</p>
<hr>
</div>
<!-- ABOUT THE PROJECT -->

## About the project
This project was made by:
<table>
    <tr>
        <td>Name</td>
        <td>Description</td>
    </tr>
    <tr>
        <td>Clerget Mathys</td>
        <td><a href="https://github.com/MathysC/"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&amp;logo=github&amp;logoColor=white" alt="GitHub"></a></td>
    </tr>
    <tr>
        <td>David Geoffrey</td>
        <td><a href="https://github.com/Polluxin/"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&amp;logo=github&amp;logoColor=white" alt="GitHub"></a></td>
    </tr>
    <tr>
        <td>Grenier Armand</td>
        <td><a href="https://github.com/Moutontone/"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&amp;logo=github&amp;logoColor=white" alt="GitHub"></a></td>
    </tr>
</table>

You can access the documentation here 

[![Github Pages](https://img.shields.io/badge/github%20pages-121013?style=for-the-badge&logo=github&logoColor=white)](https://mathysc.github.io/Jandas/)

<p align="right">(<a href="#top">back to top</a>)</p><hr>

## The features provided by our service
- Creation of DataFrame from a defined csv file, or from a set of columns.
- Display of a whole or partial dataframe (last/first lines)
- Creation of a new DataFrame from an existing one. We can choose the columns and lines that we want to keep.
- Statistics functions on columns (mean, max, min)
<p align="right">(<a href="#top">back to top</a>)</p><hr>

## The tool used
### Github Actions

#### Continuous Integration
We created a Github workflow, which is launch at every `pull_request` on `dev`, that check the coverage of our code and if the coverage is above **80%**:
- The project is built and upload as an *artifact*.
- The project is deploy as a *package* that you can use in another maven project. 

#### Github Pages automation
We created a Github workflow, which is launch at every `pull_request` on `dev` that will do these steps:
- Generate the **javadoc**
- Generate the **jacoco report** 
- Copy both in the `docs/` folder
- Build and Deploy the `Github pages` using our `README.MD` as `index.html` page.
<p align="right">(<a href="#top">back to top</a>)</p><hr>

## Git procedure

We separate the git in **3** parts:
1. `prod` branch, what we have released for Jandas (everything is tested and approved).
2. `dev` branch, what we are working on.
3. Several `features` branch, that we merge into `dev` when finished.


We were working on `features` and `CI` jobs at the same time. Everytime we thought that the work is done for this part, we create a `pull request`, the others developers will review our code and then approved, or no, the changes.

<p align="right">(<a href="#top">back to top</a>)</p><hr>

## Docker 

<a href="https://hub.docker.com/repository/docker/mathysclerget/jandas/general"><img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)" alt="Docker"></a>

You can find here a simple *Docker repository* with an image that launch automatically the main in `Main.java`.

The instruction are:
```bash
docker pull mathysclerget/jandas:latest
```
```bash
docker run mathysclerget/jandas:latest
```
<p align="right">(<a href="#top">back to top</a>)</p><hr>

## Feedback

We found this project very interesting and very formative.

We sometimes have some problems with certain files like `./idea/workspace.xml` which despite the `.gitignore` continued to be added.

It was very interesting to do the code reviews together, to be able to discuss the modifications to be made, the things to add or remove. While seeing the github actions running in the background.

It was very satisfying to see all the workflows running, seeing the *github pages* refreshing, the *package* and the *release* being created and the docker hub updating in ***one*** action on our part.

<p align="right">(<a href="#top">back to top</a>)</p><hr>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- [README.MD Template](https://github.com/othneildrew/Best-README-Template)
<p align="right">(<a href="#top">back to top</a>)</p>
