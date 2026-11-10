import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { toQuery } from "@/shared/api/to-query";
import type { Page } from "@/shared/types";
import type { Product } from "@/modules/products/types";

type Query = { search?: string; categoryId?: string; page?: number; size?: number };

export function getProducts({ search, categoryId, page = 0, size = 20 }: Query = {}) {
  return apiFetch<Page<Product>>(`/products${toQuery({ search, categoryId, page, size })}`);
}
