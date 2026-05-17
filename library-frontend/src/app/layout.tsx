import "./globals.css";
import Link from "next/link";

export const metadata = {
    title: "도서 관리 시스템",
    description: "Next.js 15와 스프링 부트로 만든 모던 도서 관리 시스템",
};

export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode;
}) {
    return (
        <html lang="ko">
        <body className="bg-[#f8fafc] text-slate-900 antialiased min-h-screen">
        {/* 모던 네비게이션 바 */}
        <nav className="bg-white border-b border-slate-200 sticky top-0 z-50 shadow-sm">
            <div className="max-w-5xl mx-auto px-6 h-16 flex justify-between items-center">
                {/* 브랜드 로고 */}
                <Link href="/" className="flex items-center gap-2.5 group">
                    <div className="w-9 h-9 bg-gradient-to-tr from-blue-600 to-indigo-500 rounded-xl flex items-center justify-center shadow-md text-lg">
                        📚
                    </div>
                    <span className="font-bold text-lg tracking-tight text-slate-900 group-hover:text-blue-600 transition-colors">
                도서 관리 시스템
              </span>
                </Link>

                {/* 상단 액션 버튼 */}
                <Link
                    href="/register"
                    className="inline-flex items-center gap-1.5 bg-slate-900 hover:bg-blue-600 text-white font-semibold px-4 py-2 rounded-xl transition-all duration-200 active:scale-95 text-sm"
                >
                    <span className="text-base font-medium">+</span> 도서 등록하기
                </Link>
            </div>
        </nav>

        {/* 메인 대시보드 컨테이너 */}
        <main className="max-w-5xl mx-auto px-6 py-10">
            {children}
        </main>
        </body>
        </html>
    );
}