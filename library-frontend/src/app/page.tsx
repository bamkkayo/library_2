import BookCard from "../components/BookCard";

interface Book {
    id: number;
    title: string;
    author: string;
    price: number;
    available: boolean;
}

async function getBooks(): Promise<Book[]> {
    try {
        const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/books`, {
            cache: "no-store",
        });
        if (!res.ok) return [];
        return res.json();
    } catch (error) {
        console.error("백엔드 API 연결 실패:", error);
        return [];
    }
}

export default async function HomePage() {
    const books = await getBooks();

    return (
        <div className="space-y-8">
            {/* 대시보드 요약 헤더 상자 */}
            <div className="bg-white border border-slate-200 p-6 rounded-2xl shadow-sm flex flex-col sm:flex-row justify-between sm:items-center gap-4">
                <div>
                    <span className="text-xs font-bold text-blue-600 uppercase tracking-wider">Dashboard</span>
                    <h1 className="text-2xl font-extrabold text-slate-900 tracking-tight mt-0.5">도서 현황 목록</h1>
                </div>
                <div className="flex gap-4">
                    <div className="bg-slate-50 border border-slate-100 px-4 py-2.5 rounded-xl min-w-[100px]">
                        <p className="text-[11px] font-medium text-slate-400">전체 도서 수</p>
                        <p className="text-xl font-bold text-slate-800 mt-0.5">{books.length}권</p>
                    </div>
                    <div className="bg-green-50 border border-green-100 px-4 py-2.5 rounded-xl min-w-[100px]">
                        <p className="text-[11px] font-medium text-green-600">대출 가능</p>
                        <p className="text-xl font-bold text-green-700 mt-0.5">
                            {books.filter(b => b.available).length}권
                        </p>
                    </div>
                </div>
            </div>

            {/* 메인 목록 콘텐츠 영역 */}
            {books.length === 0 ? (
                <div className="bg-white border border-slate-200 rounded-3xl p-16 text-center max-w-xl mx-auto shadow-sm mt-8">
                    <div className="w-20 h-20 bg-blue-50 text-blue-600 rounded-3xl flex items-center justify-center text-3xl mx-auto mb-6">
                        📖
                    </div>
                    <h3 className="text-xl font-bold text-slate-900 tracking-tight">도서관이 비어있습니다</h3>
                    <p className="text-slate-400 text-sm mt-2 max-w-sm mx-auto leading-relaxed">
                        아직 시스템에 등록된 도서 정보가 없습니다.<br />
                        오른쪽 상단의 버튼을 눌러 첫 번째 도서를 등록해 보세요!
                    </p>
                    <div className="mt-6">
                        <a
                            href="/register"
                            className="inline-flex text-sm font-bold text-blue-600 bg-blue-50 hover:bg-blue-100 px-4 py-2 rounded-xl transition"
                        >
                            지금 등록하러 가기 &rarr;
                        </a>
                    </div>
                </div>
            ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
                    {books.map((book) => (
                        <BookCard key={book.id} {...book} />
                    ))}
                </div>
            )}
        </div>
    );
}