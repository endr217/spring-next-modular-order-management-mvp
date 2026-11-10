import { ApiError } from "@/shared/api/api-error";

type ErrorBody = { message?: string; errors?: Record<string, string> };

const toFieldPath = (field: string) => field.replace(/\[(\d+)\]/g, ".$1");

export async function toApiError(response: Response) {
  const body: ErrorBody = await response.json().catch(() => ({}));
  const errors = Object.fromEntries(
    Object.entries(body.errors ?? {}).map(([field, message]) => [toFieldPath(field), message]),
  );
  return new ApiError(response.status, body.message ?? "", errors);
}
