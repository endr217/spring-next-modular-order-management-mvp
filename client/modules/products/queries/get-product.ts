import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { orNotFound } from "@/shared/api/or-not-found";
import type { Product } from "@/modules/products/types";

export function getProduct(id: string) {
  return orNotFound(apiFetch<Product>(`/products/${encodeURIComponent(id)}`));
}
