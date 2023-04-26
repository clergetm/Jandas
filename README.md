<div id="top"></div>

<!-- TITLE -->
<div align="center">
<h1 align="center">Jandas</h1>

  <p align="center">
    Java version of Pandas python library
  </p>

[![CI](https://github.com/MathysC/Jandas/actions/workflows/CI.yml/badge.svg?branch=dev)][ci-action]
[![Deploy github pages](https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml/badge.svg?branch=dev)][site-action]
[![Coverage](.github/badges/jacoco.svg)][ci-action]
[![Branche](.github/badges/branches.svg)][ci-action]
[![Javadoc](https://img.shields.io/badge/-javadoc-yellow)](https://mathysc.github.io/Jandas/docs/javadoc)
[![Jacoco](https://img.shields.io/badge/-jacoco-red)](https://mathysc.github.io/Jandas/docs/jacoco )
</div>

<!-- ABOUT THE PROJECT -->

## About the project
This project was made by:
| Name           | Description                                                                                                                                    |
| -------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| Clerget Mathys | [![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/MathysC/)    |
| David Geoffrey | [![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Polluxin/)   |
| Grenier Armand | [![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Moutontone/) |

You can access the documentation here [![Github Pages](https://img.shields.io/badge/github%20pages-121013?style=for-the-badge&logo=github&logoColor=white)][site]
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

- [Markdown Cheatsheet][md-url]
- [README.MD Template][readme-url]
<p align="right">(<a href="#top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[md-url]: https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
[readme-url]: https://github.com/othneildrew/Best-README-Template
[ci-action]: https://github.com/MathysC/Jandas/actions/workflows/CI.yml
[site-action]: https://github.com/MathysC/Jandas/actions/workflows/generate-site.yml
[site]: https://mathysc.github.io/Jandas/
