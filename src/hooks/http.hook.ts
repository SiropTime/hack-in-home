import { useCallback, useState } from "react";

export enum RequestType {
  Get = "GET",
  Post = "POST",
  Delete = "DELETE",
}

interface request {
  url: string;
  method?: RequestType;
  body?: BodyInit | null;
  headers?: HeadersInit;
}

export const useHttp = () => {
  const [loading, setLoading] = useState<boolean | null>(false);
  const [error, setError] = useState<boolean | null>(false);

  const request = useCallback(
    async ({
      url,
      method = RequestType.Get,
      body = null,
      headers = { "Content-Type": "application/json" },
    }: request) => {
      setLoading(true);

      try {
        const response = await fetch(url, { method, body, headers });

        if (!response.ok) {
          throw new Error(`Could not fetch ${url}, status: ${error}`);
        }

        const data = response.json();

        setLoading(false);

        return data;
      } catch (e) {
        setLoading(false);
        setError(true);
        throw e;
      }
    },
    []
  );

  const clearError = useCallback(() => setError(null), []);

  return { loading, error, request, clearError };
};
