import { NextResponse, type NextRequest } from "next/server";
import { clearToken } from "@/shared/auth/session";

export async function POST(request: NextRequest) {
  await clearToken();
  return NextResponse.redirect(new URL("/login", request.url), 303);
}
