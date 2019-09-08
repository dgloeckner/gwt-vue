export default interface IGithubApi {
  fetchResults(query: String): Promise<any>
}