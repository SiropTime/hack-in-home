import { RequestType, useHttp } from "../hooks";

const useAdminService = () => {
  const { loading, error, request, clearError } = useHttp();
  const _apiBase = "http://25.6.173.125:8080/";

  const getFaq = () => {
    const res = request({ url: `${_apiBase}faq/all` });
    return res;
  };

  const addFag = async (body: any) => {
    await request({
      url: `${_apiBase}faq/save`,
      method: RequestType.Post,
      body,
    });
  };

  const removeFaq = async (body: any) => {
    await request({
      url: `${_apiBase}faq/${body.id}`,
      method: RequestType.Delete,
      body,
    });
  };

  const getSubjects = async () => {
    const res = await request({ url: `${_apiBase}subject/list` });
    return res;
  };

  const getRequisition = async () => {
    const res = await request({ url: `${_apiBase}requisition/list` });
    return res;
  };

  return {
    loading,
    error,
    clearError,
    getFaq,
    addFag,
    removeFaq,
    getSubjects,
    getRequisition,
  };
};

export default useAdminService;
