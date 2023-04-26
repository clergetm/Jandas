<div id="top"></div>

<!-- TITLE -->
<div align="center">
<h1 align="center">Jandas</h1>

  <p align="center">
    Java version of Pandas python library
  </p>


<a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://github.com/MathysC/Jandas/actions/workflows/CI.yml/badge.svg?branch=dev" alt="CI"></a> <a href="https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml"><img src="https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml/badge.svg?branch=dev" alt="Deploy github pages"></a> <a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://mathysc.github.io/Jandas/docs/badges/jacoco.svg" alt="CI"></a>  <a href="https://github.com/MathysC/Jandas/actions/workflows/CI.yml"><img src="https://mathysc.github.io/Jandas/docs/badges/branches.svg" alt="Branches"></a> <a href="https://mathysc.github.io/Jandas/docs/javadoc"><img src="https://img.shields.io/badge/-javadoc-yellow" alt="Javadoc"></a> <a href="https://mathysc.github.io/Jandas/docs/jacoco"><img src="https://img.shields.io/badge/-jacoco-red" alt="Jacoco"></a>
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

You can access the documentation here [![Github Pages](https://img.shields.io/badge/github%20pages-121013?style=for-the-badge&logo=github&logoColor=white)](https://mathysc.github.io/Jandas/)
<p align="right">(<a href="#top">back to top</a>)</p>

## The features provided by our service

TODO: Copy this part in package description https://github.com/MathysC/Jandas/packages
<p align="right">(<a href="#top">back to top</a>)</p>

## The tool used
##### A description of the tool choices you have made.

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
<p align="right">(<a href="#top">back to top</a>)</p>

## Git procedure
##### A description of the git workflow that you have set up for your project, and of the Pull/Merge request validation procedure that you have adopted.

We separate the git in **3** parts:
1. `prod` branch, what we have released for Jandas (everything is tested and approved).
2. `dev` branch, what we are working on.
3. Several `features` branch, that we merge into `dev` when finished.


We were working on `features` and `CI` jobs at the same time. Everytime we thought that the work is done for this part, we create a `pull request`, the others developers will review our code and then approved, or no, the changes.

<p align="right">(<a href="#top">back to top</a>)</p>

## Docker
##### If applicable, a list and a short description of the Docker images produced and a link to their repository.

<p align="right">(<a href="#top">back to top</a>)</p>

## Feedback
##### A feedback section in which you will give your feedback on the different tools used during the project.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- [Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- [README.MD Template](https://github.com/othneildrew/Best-README-Template)
<p align="right">(<a href="#top">back to top</a>)</p>
