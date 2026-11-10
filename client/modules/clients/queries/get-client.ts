import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { orNotFound } from "@/shared/api/or-not-found";
import type { Client } from "@/modules/clients/types";

export function getClient(id: string) {
  return orNotFound(apiFetch<Client>(`/clients/${encodeURIComponent(id)}`));
}
