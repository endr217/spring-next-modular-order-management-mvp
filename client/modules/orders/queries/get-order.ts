import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { orNotFound } from "@/shared/api/or-not-found";
import type { Order } from "@/modules/orders/types";

export function getOrder(id: string) {
  return orNotFound(apiFetch<Order>(`/orders/${encodeURIComponent(id)}`));
}
