import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { toQuery } from "@/shared/api/to-query";
import type { Client } from "@/modules/clients/types";
import type { Page } from "@/shared/types";

type Query = { search?: string; page?: number; size?: number };

export function getClients({ search, page = 0, size = 20 }: Query = {}) {
  return apiFetch<Page<Client>>(`/clients${toQuery({ search, page, size })}`);
}
