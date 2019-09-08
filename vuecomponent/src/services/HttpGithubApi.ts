import axios from "axios";
import IGithubApi from "./IGithubApi";

export default class GithubApiService implements IGithubApi {
  public fetchResults(query: String): Promise<any> {
    const url = `https://api.github.com/search/repositories?q=${query}&sort=stars&order=desc`;
    return axios.get(url);
  }
}
